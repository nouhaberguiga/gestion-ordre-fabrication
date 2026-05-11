import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MachineService } from '../../services/machine.service';
import { Machine } from '../../models/models';

@Component({
  selector: 'app-machines',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './machines.component.html'
})
export class MachinesComponent implements OnInit {

  machines: Machine[] = [];
  msg = '';
  msgType = 'success';
  showForm = false;
  editing = false;
  editId: number | null = null;

  form: Machine = { name: '', status: 'AVAILABLE', lastMaintenance: '' };

  constructor(private service: MachineService) {}

  ngOnInit() { this.load(); }

  load() {
    this.service.getAll().subscribe({
      next: data => this.machines = data,
      error: () => setTimeout(() => this.alert('Erreur de chargement', 'danger'), 100)
    });
  }

  openCreate() {
    this.form = { name: '', status: 'AVAILABLE', lastMaintenance: '' };
    this.editing = false; this.editId = null; this.showForm = true;
  }

  openEdit(m: Machine) {
    this.form = { ...m };
    this.editId = m.id!; this.editing = true; this.showForm = true;
  }

  save() {
    if (!this.form.name) return this.alert('Le nom est obligatoire', 'danger');
    const obs = this.editing
      ? this.service.update(this.editId!, this.form)
      : this.service.create(this.form);
    obs.subscribe({
      next: () => { this.alert(this.editing ? 'Machine modifiée' : 'Machine créée'); this.showForm = false; this.load(); },
      error: err => this.alert(err.error?.error || 'Erreur', 'danger')
    });
  }

  delete(id: number) {
    if (!confirm('Supprimer cette machine ?')) return;
    this.service.delete(id).subscribe({
      next: () => { this.alert('Machine supprimée'); this.load(); },
      error: err => this.alert(err.error?.error || 'Erreur', 'danger')
    });
  }

  changeStatus(id: number, action: string) {
    const obs: any =
      action === 'available'    ? this.service.setAvailable(id) :
      action === 'maintenance'  ? this.service.setMaintenance(id) :
      action === 'in-use'       ? this.service.setInUse(id) :
                                  this.service.setBroken(id);
    obs.subscribe({
      next: () => { this.alert('Statut mis à jour'); this.load(); },
      error: (err: any) => this.alert(err.error?.error || 'Erreur', 'danger')
    });
  }

  badgeClass(status?: string) {
    return status === 'AVAILABLE' ? 'bg-success' :
           status === 'IN_USE'    ? 'bg-primary' :
           status === 'MAINTENANCE'? 'bg-warning text-dark' : 'bg-danger';
  }

  alert(text: string, type = 'success') {
    this.msg = text; this.msgType = type;
    setTimeout(() => this.msg = '', 3000);
  }
}
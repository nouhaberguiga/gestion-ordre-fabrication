import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EmployeeService } from '../../services/employee.service';
import { MachineService } from '../../services/machine.service';
import { Employee, Machine } from '../../models/models';

@Component({
  selector: 'app-employees',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './employees.component.html'
})
export class EmployeesComponent implements OnInit {

  employees: Employee[] = [];
  machines: Machine[] = [];
  msg = '';
  msgType = 'success';
  showForm = false;
  editing = false;
  editId: number | null = null;

  form: Employee = { firstName: '', lastName: '', role: '', available: true, machineId: null };

  constructor(
    private service: EmployeeService,
    private machineService: MachineService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.load();
    this.loadMachines();
  }

  load() {
    this.service.getAll().subscribe({
      next: data => {
        this.employees = data;
        this.cdr.detectChanges();
      },
      error: () => setTimeout(() => this.alert('Erreur de chargement', 'danger'))
    });
  }

  loadMachines() {
    this.machineService.getAll().subscribe({
      next: data => {
        this.machines = data;
        this.cdr.detectChanges();
      },
      error: () => setTimeout(() => this.alert('Erreur chargement machines', 'danger'))
    });
  }

  openCreate() {
    this.form = { firstName: '', lastName: '', role: '', available: true, machineId: null };
    this.editing = false; this.editId = null; this.showForm = true;
  }

  openEdit(e: Employee) {
    this.form = { ...e };
    this.editId = e.id!; this.editing = true; this.showForm = true;
  }

  save() {
    if (!this.form.firstName || !this.form.lastName || !this.form.role)
      return this.alert('Tous les champs sont obligatoires', 'danger');
    const obs = this.editing
      ? this.service.update(this.editId!, this.form)
      : this.service.create(this.form);
    obs.subscribe({
      next: () => { this.alert(this.editing ? 'Employé modifié' : 'Employé créé'); this.showForm = false; this.load(); },
      error: err => this.alert(err.error?.error || 'Erreur', 'danger')
    });
  }

  delete(id: number) {
    if (!confirm('Supprimer cet employé ?')) return;
    this.service.delete(id).subscribe({
      next: () => { this.alert('Employé supprimé'); this.load(); },
      error: err => this.alert(err.error?.error || 'Erreur', 'danger')
    });
  }

  toggle(e: Employee) {
    const obs = e.available ? this.service.setUnavailable(e.id!) : this.service.setAvailable(e.id!);
    obs.subscribe({
      next: () => { this.alert('Disponibilité mise à jour'); this.load(); },
      error: (err: any) => this.alert(err.error?.error || 'Erreur', 'danger')
    });
  }

  alert(text: string, type = 'success') {
    this.msg = text; this.msgType = type;
    this.cdr.detectChanges();
    setTimeout(() => { this.msg = ''; this.cdr.detectChanges(); }, 3000);
  }

  getMachineName(employee: Employee): string {
    if (employee.machineName) return employee.machineName;
    const machine = this.machines.find(m => m.id === employee.machineId);
    return machine ? machine.name : 'Non assignée';
  }
}

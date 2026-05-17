import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OrderService } from '../../services/order.service';
import { ProductService } from '../../services/product.service';
import { MachineService } from '../../services/machine.service';
import { EmployeeService } from '../../services/employee.service';
import { Order, OrderRequest, Product, Machine, Employee } from '../../models/models';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './orders.component.html'
})
export class OrdersComponent implements OnInit {

  orders: Order[] = [];
  products: Product[] = [];
  machines: Machine[] = [];
  employees: Employee[] = [];
  msg = '';
  msgType = 'success';
  showForm = false;
  editing = false;
  editId: number | null = null;
  orderStatuses = ['PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED', 'ON_HOLD'];

  form: {
    project: string;
    quantity: number;
    productId: number | null;
    machineId: number | null;
    employeeIds: number[];
  } = {
    project: '',
    quantity: 0,
    productId: null,
    machineId: null,
    employeeIds: []
  };

  constructor(
    private orderService: OrderService,
    private productService: ProductService,
    private machineService: MachineService,
    private employeeService: EmployeeService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.load();
    this.loadProducts();
    this.loadMachines();
    this.loadEmployees();
  }

  load() {
    this.orderService.getAll().subscribe({
      next: data => {
        this.orders = data;
        this.cdr.detectChanges();
      },
      error: () => setTimeout(() => this.alert('Erreur de chargement', 'danger'), 100)
    });
  }

  loadProducts() {
    this.productService.getAll().subscribe({
      next: data => {
        this.products = data;
        this.cdr.detectChanges();
      },
      error: () => setTimeout(() => this.alert('Erreur chargement produits', 'danger'), 100)
    });
  }

  loadMachines() {
    this.machineService.getAll().subscribe({
      next: data => {
        this.machines = data;
        this.cdr.detectChanges();
      },
      error: () => setTimeout(() => this.alert('Erreur chargement machines', 'danger'), 100)
    });
  }

  loadEmployees() {
    this.employeeService.getAll().subscribe({
      next: data => {
        this.employees = data;
        this.cdr.detectChanges();
      },
      error: () => setTimeout(() => this.alert('Erreur chargement employés', 'danger'), 100)
    });
  }

  openCreate() {
    this.form = {
      project: '',
      quantity: 0,
      productId: null,
      machineId: null,
      employeeIds: []
    };
    this.editing = false;
    this.editId = null;
    this.showForm = true;
  }

  openEdit(o: Order) {
    this.form = {
      project: o.project,
      quantity: o.quantity,
      productId: o.productId ?? null,
      machineId: o.machineId ?? null,
      employeeIds: o.employeeIds || []
    };
    this.editId = o.id!;
    this.editing = true;
    this.showForm = true;
  }

  save() {
    // Validation
    if (!this.form.project || !this.form.project.trim()) {
      return this.alert('Le nom du projet est obligatoire', 'danger');
    }
    if (!this.form.quantity || this.form.quantity <= 0) {
      return this.alert('La quantité doit être supérieure à 0', 'danger');
    }
    if (!this.form.productId) {
      return this.alert('Veuillez sélectionner un produit', 'danger');
    }
    if (!this.form.machineId) {
      return this.alert('Veuillez sélectionner une machine', 'danger');
    }
    if (!this.form.employeeIds || this.form.employeeIds.length === 0) {
      return this.alert('Veuillez sélectionner au moins un employé', 'danger');
    }

    const request: OrderRequest = {
      project: this.form.project.trim(),
      quantity: this.form.quantity,
      productId: this.form.productId!,
      machineId: this.form.machineId!,
      employeeIds: this.form.employeeIds
    };

    this.orderService.create(request).subscribe({
      next: () => {
        this.alert('Ordre créé avec succès', 'success');
        this.showForm = false;
        this.load();
      },
      error: err => this.alert(err.error?.message || 'Erreur de création', 'danger')
    });
  }

  delete(id: number) {
    if (!confirm('Confirmer la suppression de cet ordre ?')) return;
    this.orderService.delete(id).subscribe({
      next: () => {
        this.alert('Ordre supprimé', 'success');
        this.load();
      },
      error: err => this.alert('Erreur de suppression', 'danger')
    });
  }

  start(id: number) {
    this.orderService.start(id).subscribe({
      next: () => {
        this.alert('Ordre démarré', 'success');
        this.load();
      },
      error: () => this.alert('Erreur de démarrage', 'danger')
    });
  }

  finish(id: number) {
    this.orderService.finish(id).subscribe({
      next: () => {
        this.alert('Ordre terminé', 'success');
        this.load();
      },
      error: () => this.alert('Erreur de finalisation', 'danger')
    });
  }

  hold(id: number) {
    this.orderService.hold(id).subscribe({
      next: () => {
        this.alert('Ordre mis en pause', 'success');
        this.load();
      },
      error: () => this.alert('Erreur de mise en pause', 'danger')
    });
  }

  resume(id: number) {
    this.orderService.resume(id).subscribe({
      next: () => {
        this.alert('Ordre repris', 'success');
        this.load();
      },
      error: () => this.alert('Erreur de reprise', 'danger')
    });
  }

  cancel(id: number) {
    this.orderService.cancel(id).subscribe({
      next: () => {
        this.alert('Ordre annulé', 'success');
        this.load();
      },
      error: () => this.alert('Erreur d\'annulation', 'danger')
    });
  }

  changeStatus(order: Order, status: string) {
  if (!order.id || order.status === status) return;

  this.orderService.updateStatus(order.id, status).subscribe({
    next: updated => {
      order.status = updated.status;
      this.alert('État mis à jour', 'success');
      this.load(); // ✅ correct ici
    },
    error: err => {
      this.alert(err.error?.message || 'Erreur de modification de l\'état', 'danger');
      this.load();
    }
  });
}

  alert(text: string, type = 'success') {
    this.msg = text;
    this.msgType = type;
    this.cdr.detectChanges();
    setTimeout(() => {
      this.msg = '';
      this.cdr.detectChanges();
    }, 3000);
  }

  // Helper methods for template
  getProductName(id: number): string {
    const product = this.products.find(p => p.id === id);
    return product ? product.name : 'Produit inconnu';
  }

  getMachineName(id: number): string {
    const machine = this.machines.find(m => m.id === id);
    return machine ? machine.name : 'Machine inconnue';
  }

  getEmployeeNames(ids: number[]): string {
    if (!ids || ids.length === 0) return 'Aucun employé';
    return ids.map(id => {
      const emp = this.employees.find(e => e.id === id);
      return emp ? `${emp.firstName} ${emp.lastName}` : `ID ${id}`;
    }).join(', ');
  }

  getEmployeesByMachineName(machineId?: number): string {
    if (!machineId) return 'Aucune machine';

    const assignedEmployees = this.employees
      .filter(employee => employee.machineId === machineId)
      .map(employee => `${employee.firstName} ${employee.lastName}`);

    return assignedEmployees.length ? assignedEmployees.join(', ') : 'Aucun employé assigné';
  }

  getStatusLabel(status?: string): string {
    const labels: Record<string, string> = {
      PENDING: 'Créé',
      IN_PROGRESS: 'En cours',
      COMPLETED: 'Terminé',
      CANCELLED: 'Annulé',
      ON_HOLD: 'En pause'
    };

    return status ? labels[status] || status : '-';
  }

  onEmployeeChange(employeeId: number, checked: boolean) {
    if (checked) {
      if (!this.form.employeeIds.includes(employeeId)) {
        this.form.employeeIds.push(employeeId);
      }
    } else {
      this.form.employeeIds = this.form.employeeIds.filter((id: number) => id !== employeeId);
    }
  }

  isEmployeeSelected(employeeId: number): boolean {
    return this.form.employeeIds.includes(employeeId);
  }
}

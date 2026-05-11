import { Routes } from '@angular/router';

import { ProductsComponent } from './components/products/products.component';
import { MachinesComponent } from './components/machines/machines.component';
import { EmployeesComponent } from './components/employees/employees.component';
import { OrdersComponent } from './components/orders/orders.component';

export const routes: Routes = [
  { path: '', redirectTo: 'products', pathMatch: 'full' },

  { path: 'products', component: ProductsComponent },

  { path: 'machines', component: MachinesComponent },

  { path: 'employees', component: EmployeesComponent },

  { path: 'orders', component: OrdersComponent }
];
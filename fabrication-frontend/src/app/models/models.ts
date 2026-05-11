// src/app/models/models.ts

export interface Product {
  id?: number;
  name: string;
  type: string;
  reference: string;
  price: number;
  quantityStock: number;
  supplier: string;
}

export interface Machine {
  id?: number;
  name: string;
  status?: string;
  lastMaintenance?: string;
}

export interface Employee {
  id?: number;
  firstName: string;
  lastName: string;
  role: string;
  available?: boolean;
  machineId?: number | null;
  machineName?: string;
}

export interface Order {
  id?: number;
  project: string;
  quantity: number;
  date?: string;
  status?: string;
  productId?: number;
  productName?: string;
  machineId?: number;
  machineName?: string;
  employeeIds?: number[];
}

export interface OrderRequest {
  project: string;
  quantity: number;
  productId: number;
  machineId: number;
  employeeIds: number[];
}

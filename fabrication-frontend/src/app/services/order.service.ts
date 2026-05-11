import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order, OrderRequest } from '../models/models';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  url = environment.apiUrl + '/orders';

  constructor(private http: HttpClient) {}

  // GET ALL ORDERS
  getAll(): Observable<Order[]> {
    return this.http.get<Order[]>(this.url);
  }

  // CREATE ORDER
  create(req: OrderRequest): Observable<Order> {
    return this.http.post<Order>(this.url, req);
  }

  // DELETE ORDER
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  // START ORDER
  start(id: number): Observable<Order> {
    return this.http.put<Order>(`${this.url}/start/${id}`, {});
  }

  // FINISH ORDER
  finish(id: number): Observable<Order> {
    return this.http.put<Order>(`${this.url}/finish/${id}`, {});
  }

  // HOLD ORDER
  hold(id: number): Observable<Order> {
    return this.http.put<Order>(`${this.url}/hold/${id}`, {});
  }

  // RESUME ORDER
  resume(id: number): Observable<Order> {
    return this.http.put<Order>(`${this.url}/resume/${id}`, {});
  }

  // UPDATE STATUS (IMPORTANT FIX)
  updateStatus(id: number, status: string): Observable<Order> {
    switch (status) {
      case 'IN_PROGRESS':
        return this.start(id);
      case 'COMPLETED':
        return this.finish(id);
      case 'ON_HOLD':
        return this.hold(id);
      case 'CANCELLED':
        return this.cancel(id);
      default:
        return this.http.put<Order>(`${this.url}/${id}/status`, {}, { params: { status } });
    }
  }

  // CANCEL ORDER
  cancel(id: number): Observable<Order> {
    return this.http.put<Order>(`${this.url}/cancel/${id}`, {});
  }
}

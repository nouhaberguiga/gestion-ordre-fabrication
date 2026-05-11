import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order, OrderRequest } from '../models/models';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class OrderService {
  url = environment.apiUrl + '/orders';
  constructor(private http: HttpClient) {}
  getAll(): Observable<Order[]>        { return this.http.get<Order[]>(this.url); }
  create(req: OrderRequest): Observable<Order>  { return this.http.post<Order>(this.url, req); }
  delete(id: number)                   { return this.http.delete(`${this.url}/${id}`); }
  start(id: number)                    { return this.http.put(`${this.url}/start/${id}`, {}); }
  finish(id: number)                   { return this.http.put(`${this.url}/finish/${id}`, {}); }
  cancel(id: number)                   { return this.http.put(`${this.url}/cancel/${id}`, {}); }
}
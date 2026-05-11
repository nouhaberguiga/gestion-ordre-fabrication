import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/models';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ProductService {
  url = 'http://localhost:8080/products';
  constructor(private http: HttpClient) {}
  getAll(): Observable<Product[]>         { return this.http.get<Product[]>(this.url); }
  getById(id: number): Observable<Product>{ return this.http.get<Product>(`${this.url}/${id}`); }
  create(p: Product): Observable<Product> { return this.http.post<Product>(this.url, p); }
  update(id: number, p: Product)          { return this.http.put<Product>(`${this.url}/${id}`, p); }
  delete(id: number)                      { return this.http.delete(`${this.url}/${id}`); }
}
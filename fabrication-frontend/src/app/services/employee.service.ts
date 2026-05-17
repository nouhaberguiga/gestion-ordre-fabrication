import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../models/models';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class EmployeeService {

  url = environment.apiUrl + '/employees';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.url);
  }

  getById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.url}/${id}`);
  }

  create(e: Employee): Observable<Employee> {

    const body = { ...e };

    delete body.id;

    return this.http.post<Employee>(this.url, body);
  }

  update(id: number, e: Employee) {
    return this.http.put<Employee>(`${this.url}/${id}`, e);
  }

  delete(id: number) {
    return this.http.delete(`${this.url}/${id}`);
  }

  setAvailable(id: number) {
    return this.http.put(`${this.url}/available/${id}`, {});
  }

  setUnavailable(id: number) {
    return this.http.put(`${this.url}/unavailable/${id}`, {});
  }
}

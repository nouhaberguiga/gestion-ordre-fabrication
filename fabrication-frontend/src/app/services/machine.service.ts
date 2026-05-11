import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Machine } from '../models/models';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class MachineService {
  url = environment.apiUrl + '/machines';
  constructor(private http: HttpClient) {}
  getAll(): Observable<Machine[]>          { return this.http.get<Machine[]>(this.url); }
  getById(id: number): Observable<Machine> { return this.http.get<Machine>(`${this.url}/${id}`); }
  create(m: Machine): Observable<Machine>  { return this.http.post<Machine>(this.url, m); }
  update(id: number, m: Machine)           { return this.http.put<Machine>(`${this.url}/${id}`, m); }
  delete(id: number)                       { return this.http.delete(`${this.url}/${id}`); }
  setAvailable(id: number)                 { return this.http.put(`${this.url}/available/${id}`, {}); }
  setMaintenance(id: number)               { return this.http.put(`${this.url}/maintenance/${id}`, {}); }
  setInUse(id: number)                     { return this.http.put(`${this.url}/in-use/${id}`, {}); }
  setBroken(id: number)                    { return this.http.put(`${this.url}/broken/${id}`, {}); }
}
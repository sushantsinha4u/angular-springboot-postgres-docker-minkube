import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Satellites } from '../models/satellites.model';

const baseUrl = 'http://localhost:8080/api/satellites';

@Injectable({
  providedIn: 'root',
})
export class SatelliteService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Satellites[]> {
    console.log(this.http.get<Satellites[]>(baseUrl));
    return this.http.get<Satellites[]>(baseUrl);
  }

  get(id: any): Observable<Satellites> {
    return this.http.get<Satellites>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByCategory(category: any): Observable<Satellites[]> {
    return this.http.get<Satellites[]>(`${baseUrl}?category=${category}`);
  }
}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../model/customer';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private apiUrl = 'http://localhost:9090/api/v1'; 

  constructor(private http: HttpClient) { }

  getAllCustomers(page: number, size: number): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.apiUrl}/show-customers?page=${page}&size=${size}`);
  }

  saveCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(`${this.apiUrl}/save-customer`, customer, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    });
  }
}

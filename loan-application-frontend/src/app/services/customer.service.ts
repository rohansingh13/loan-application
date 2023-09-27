import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../models/customer';
import { Observable } from 'rxjs';
import { CustomerPageResponse } from '../models/customer-page-response';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private apiUrl = 'http://localhost:3040/api/v1'; 

  constructor(private http: HttpClient) { }

  getAllCustomers(page: number, size: number): Observable<CustomerPageResponse> {
    return this.http.get<CustomerPageResponse>(`${this.apiUrl}/show-customers?page=${page}&size=${size}`);
  }

  saveCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(`${this.apiUrl}/save-customer`, customer, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    });
  }
}

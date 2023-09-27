import { Customer } from "./customer";

export interface CustomerPageResponse {
  customers: Customer[]; 
  totalCustomers: number;
}

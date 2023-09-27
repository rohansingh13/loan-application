import { Component } from '@angular/core';
import { Customer } from 'src/app/models/customer';
import { CustomerPageResponse } from 'src/app/models/customer-page-response';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-adviser-view',
  templateUrl: './adviser-view.component.html',
  styleUrls: ['./adviser-view.component.scss']
})
export class AdviserViewComponent {

  customers: Customer[] = [];
  totalCustomers: number = 0;
  totalPages: number = 0;
  currentPage = 0;
  pageSize = 10;  
  //maxPage = 0;

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers(): void {
    this.customerService.getAllCustomers(this.currentPage, this.pageSize).subscribe(
      (customerPageResponse) => {
        this.customers = customerPageResponse.customers;
        this.totalPages = Math.ceil(customerPageResponse.totalCustomers / 10); 
      },
      (error) => {
        console.log(error);
      }
    );
  }

  onPrev() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getCustomers();
    }
  }

  onNext() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.getCustomers();
    }
  }


}

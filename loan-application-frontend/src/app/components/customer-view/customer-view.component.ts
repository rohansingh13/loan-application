import { Component } from '@angular/core';
import { Customer } from 'src/app/model/customer';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrls: ['./customer-view.component.scss']
})
export class CustomerViewComponent {

  customer: Customer = {
    fullName: '',
    loanAmount: 0,
    equityAmount: 0,
    salaryAmount: 0
  }
  
  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
  }

  submitForm() {
    this.customerService.saveCustomer(this.customer).subscribe(
      (savedCustomer) => {
        this.customer = savedCustomer;       
      },
      (error) => {
        console.error('Error saving customer:', error);
      }
    );
  }
}

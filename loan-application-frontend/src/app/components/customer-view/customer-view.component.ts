import { Component } from '@angular/core';
import { Customer } from 'src/app/models/customer';
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

  successMessage: string = '';
  errorMessage: string = '';
  
  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
  }

  submitForm() {
    this.customerService.saveCustomer(this.customer).subscribe(
      (savedCustomer) => {
        this.customer = savedCustomer;  
        this.successMessage = 'Customer created successfully.';
        this.errorMessage = '';      
      },
      (error) => {
        console.error('Error saving customer:', error);
        this.errorMessage = 'Error creating customer. Please try again.';
        this.successMessage = ''; 
      }
    );
  }
}

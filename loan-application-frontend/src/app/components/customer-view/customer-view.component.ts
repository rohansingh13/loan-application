import { Component } from '@angular/core';
import { Customer } from 'src/app/model/customer';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrls: ['./customer-view.component.scss']
})
export class CustomerViewComponent {

  customer: Customer = new Customer();
  isSubmitted = false;

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
  }

  submitForm() {
    this.customerService.saveCustomer(this.customer).subscribe(
      (savedCustomer) => {
        this.customer = savedCustomer;
        this.isSubmitted = true;
      },
      (error) => {
        console.error('Error saving customer:', error);
      }
    );
  }
}

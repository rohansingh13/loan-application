import { Component } from '@angular/core';
import { Customer } from 'src/app/model/customer';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-adviser-view',
  templateUrl: './adviser-view.component.html',
  styleUrls: ['./adviser-view.component.scss']
})
export class AdviserViewComponent {

  customers: Customer[] = [];
  displayedColumns: string[] = ['fullName', 'customerSsn', 'loanAmount', 'equityAmount', 'salaryAmount'];

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers(): void {
    this.customerService.getAllCustomers(0, 10).subscribe((customers) => {
      this.customers = customers;
    });
  }

}

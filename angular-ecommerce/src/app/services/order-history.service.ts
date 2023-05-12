import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderHistory } from '../common/order-history';
import { enviroment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class OrderHistoryService {
  private orderUrl = enviroment.happyShopApiUrl + '/orders';

  constructor(private httpClient: HttpClient) {}

  getOrderHistory(theEmail: string): Observable<GetResponseOrderHistory> {
    // build URL based on the customer email
    const orderHistoryUrl = `${this.orderUrl}/search/findByCustomerEmailOrderByDateCreatedDesc?email=${theEmail}`;

    return this.httpClient.get<GetResponseOrderHistory>(orderHistoryUrl);
  }
}

interface GetResponseOrderHistory {
  _embedded: {
    orders: OrderHistory[];
  };
}

import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Ticket } from '../model/Ticket';

import { delay, first, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  // spring connection
  private readonly API = 'http://localhost:8080/api/tickets/';


  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Ticket[]>(this.API).pipe(
      first(),
      //delay(5000),
      tap(tickets => console.log(tickets))
    );
  }

}

import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Ticket } from '../models/Ticket';

import { delay, first, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  // spring connection
  private readonly API = 'api/tickets';


  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Ticket[]>(this.API).pipe(
      first(),
      //delay(5000),
      tap(tickets => console.log(tickets))
    );
  }

}

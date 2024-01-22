import { Component, OnInit } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { Ticket } from '../../models/Ticket';
import { TicketsService } from '../../services/tickets.service';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-tickets-list',
  templateUrl: './tickets-list.component.html',
  styleUrls: ['./tickets-list.component.css']
})
export class TicketsListComponent implements OnInit {

  tickets$: Observable<Ticket[]>;
  displayedColumns = ['source', 'destination'];


  constructor(private ticketService: TicketsService, public dialog: MatDialog) {
    this.tickets$ = this.ticketService.list()
    .pipe(
      catchError(error => {
        this.onError('Erro ao carregar passagens.')
        // para fazer o spinner parar, precisamos retornar dados, nem que seja array vazio
        return of([])
      })
    )
   }

   onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: {
        animal: errorMsg,
      },
    });
   }

  ngOnInit(): void {
  }

}

import { Component, OnInit } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { Ticket } from '../../model/Ticket';
import { TicketsService } from '../../services/tickets.service';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tickets-list',
  templateUrl: './tickets-list.component.html',
  styleUrls: ['./tickets-list.component.css']
})
export class TicketsListComponent implements OnInit {

  tickets$: Observable<Ticket[]>;
  displayedColumns = ['source', 'destination', 'actions'];


  constructor(
      private ticketService: TicketsService,
      public dialog: MatDialog,
      private router: Router,
      private route: ActivatedRoute
    ) {
    this.tickets$ = this.ticketService.list()
    .pipe(
      catchError(error => {
        this.onError('Erro ao carregar passagens.')
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

  onAdd() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

}

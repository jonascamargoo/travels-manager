import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TicketsListComponent } from './components/tickets-list/tickets-list.component';
import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { TicketRoutingModule } from './tickets-routing.module';
import { TicketFormComponent } from './components/ticket-form/ticket-form.component';



@NgModule({
  declarations: [
    TicketsListComponent,
    TicketFormComponent
  ],
  imports: [
    CommonModule,
    TicketRoutingModule,
    AppMaterialModule,
    SharedModule,
    
  ]
})
export class TicketsModule { }
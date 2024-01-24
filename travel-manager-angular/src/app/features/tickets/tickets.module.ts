import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TicketsListComponent } from './components/tickets-list/tickets-list.component';
import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [
    TicketsListComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule,
    SharedModule
  ]
})
export class TicketsModule { }
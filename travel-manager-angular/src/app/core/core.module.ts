import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoreRoutingModule } from './core-routing.module';
import { TicketsListComponent } from './components/tickets-list/tickets-list.component';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    TicketsListComponent
  ],
  imports: [
    CommonModule,
    CoreRoutingModule,
    AppMaterialModule,
    SharedModule
  ]
})
export class CoreModule { }

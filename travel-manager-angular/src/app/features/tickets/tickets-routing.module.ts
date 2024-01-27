import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { TicketsListComponent } from "./components/tickets-list/tickets-list.component";
import { TicketFormComponent } from "./components/ticket-form/ticket-form.component";

const routes: Routes = [
    { path: '', component: TicketsListComponent},
    { path: 'new', component: TicketFormComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class TicketRoutingModule { }
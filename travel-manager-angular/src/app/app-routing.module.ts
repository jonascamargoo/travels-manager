import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  // path eh pra bater no localhost:4200/, patchMatch eh pra verificar com ou sem a barra, redirect eh pra direcionar 

  { path: '', pathMatch: 'full', redirectTo: 'passagens'},
  {
    path: 'tickets',
    loadChildren: () => import('./core/core.module').then(m => m.CoreModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

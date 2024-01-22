import { NgModule } from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';


@NgModule({
  exports: [
    MatTableModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule

  ]
})

export class AppMaterialModule { }

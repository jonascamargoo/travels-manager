import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-error-dialog',
  templateUrl: './error-dialog.component.html',
  styleUrls: ['./error-dialog.component.css']
})
export class ErrorDialogComponent implements OnInit {

  // injetando o tipo MAT_DIALOG_DATA para o data
  constructor(@Inject (MAT_DIALOG_DATA) public data: string) { }

  ngOnInit(): void {
  }

}

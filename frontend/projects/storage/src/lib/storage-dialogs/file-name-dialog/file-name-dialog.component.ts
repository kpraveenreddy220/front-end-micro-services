import {Component, Inject} from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HelpPageId} from 'projects/help/src/lib/help-panel/help-page-id';

export interface FileNameDialogData {
  title: string;
  name: string;
  helpPageId?: HelpPageId;
}

@Component({
  selector: 'lib-file-name-dialog',
  templateUrl: './file-name-dialog.component.html',
})
export class FileNameDialogComponent {

  nameForm: FormGroup;

  constructor(public dialogRef: MatDialogRef<FileNameDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: FileNameDialogData,
              private fb: FormBuilder) {
    this.nameForm = this.fb.group({
      fileName: [data.name, [
        Validators.required,
        Validators.maxLength(255),
        Validators.pattern(/^[-\w^&'@{}[\],$=!#().%+~ ]+$/)
      ]],
    });
  }

  get fileName() {
    return this.nameForm.get('fileName');
  }
}

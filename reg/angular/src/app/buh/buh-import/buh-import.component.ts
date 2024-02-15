import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-buh-import',
  standalone: true,
  imports: [
    NgIf,
    NgFor,
    FormsModule,
  ],
  templateUrl: './buh-import.component.html',
  styleUrl: './buh-import.component.scss'
})
export class BuhImportComponent implements OnInit {

  ngOnInit(): void {
    console.log('success')
  }

  file!: File;
  jsonData: any[] = [];
  firstAndLastRows: { index: number, row: any }[] = [];
  text: string = "";
  sets = {
    start: '10',
    stop: '796',
    inputDate: 'A',
    contrAgent: 'D',
    paymentDestination: 'F',
    initiatorOfPayment: 'G',
    sum: 'H',
  }

  onFileChange(event: any) {
    this.file = event.target.files[0];
    // this.convertToJSON()
  }

  convertToJSON() {
    const reader = new FileReader();
    reader.onload = (e) => {
      if (e.target !== null) {
        const data = new Uint8Array(e.target.result as ArrayBuffer);
        const workbook = XLSX.read(data, { type: 'array' });
        const sheetName = workbook.SheetNames[0];
        const sheet = workbook.Sheets[sheetName];
        // console.log(sheet);
        this.jsonData = [];
        for (let i = parseInt(this.sets.start); i !== parseInt(this.sets.stop) + 1; i++) {
          const getValueOfCell = function (letter: string): any { return sheet[letter + i].v };
          const cellToDate = function (inputDate: string): Date {
            const dateString = getValueOfCell(inputDate);
            const DMY = dateString.split('.');
            const day = DMY[0];
            const mon = DMY[1];
            const yer = DMY[2];
            const formattedDateString = `${yer}-${mon}-${day}`;
            return new Date(formattedDateString);
          };
          const row = {
            inputDate: cellToDate(this.sets.inputDate),
            contrAgent: getValueOfCell(this.sets.contrAgent),
            paymentDestination: getValueOfCell(this.sets.paymentDestination),
            initiatorOfPayment: getValueOfCell(this.sets.initiatorOfPayment),
            sum: getValueOfCell(this.sets.sum),
          };
          this.jsonData.push(row);
        }
        this.firstAndLastRows = [];
        for (let i = 0; i !== 5; i++)
          this.firstAndLastRows.push({ index: i + parseInt(this.sets.start), row: this.jsonData[i] });
        for (let i = this.jsonData.length - 5; i !== this.jsonData.length; i++)
          this.firstAndLastRows.push({ index: i + parseInt(this.sets.start), row: this.jsonData[i] });
        console.log(this.jsonData);
      }
    };
    reader.readAsArrayBuffer(this.file);
  }

  months: string[] = ['января', 'февраля', 'марта', 'апреля', 'мая', 'июня', 'июля', 'августа', 'сентября', 'октября', 'ноября', 'декабря'];
  dateToString(date: Date) {
    const day = date.getDate();
    const month = this.months[date.getMonth()];
    const year = date.getFullYear();
    return `${day} ${month} ${year} г.`;
  }

}

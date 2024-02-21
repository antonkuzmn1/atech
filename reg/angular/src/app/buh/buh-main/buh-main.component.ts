import { DatePipe, JsonPipe, NgClass, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbDateStruct, NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxMaskDirective } from 'ngx-mask';
import { BuhDropdownList } from '../buh-dropdown-list';
import { BuhMainFilter } from './buh-main-filter';
import { BuhMainTable } from './buh-main-table';
import { BuhMainService } from './buh-main.service';

@Component({
  selector: 'app-buh-main',
  standalone: true,
  imports: [
    NgFor,
    NgIf,
    FormsModule,
    NgClass,
    NgxMaskDirective,
    NgbDatepickerModule,
    JsonPipe,
    DatePipe
  ],
  // providers: [
  //   provideNgxMask(),
  // ],
  templateUrl: './buh-main.component.html',
  styleUrl: './buh-main.component.scss'
})
export class BuhMainComponent implements OnInit {
  constructor(
    private service: BuhMainService,
    public filter: BuhMainFilter
  ) { }
  table: BuhMainTable[] = []
  editedRows: BuhMainTable[] = []
  dd: BuhDropdownList = new BuhDropdownList()
  contractors: { id: number, name: string }[] = []
  initiators: { id: number, name: string }[] = []
  isChanged: boolean = false
  filterIsOpened: boolean[] = [false, false, false, false, false, false, false, false, false, false, false, false]

  ngOnInit(): void {
    this.service.get().subscribe({
      next: (data: BuhMainTable[]) => {
        this.table = data
      }
    })
    this.service.getDropdowns().subscribe({
      next: (data: BuhDropdownList) => {
        this.dd = data
      }
    })
    this.service.getContractors().subscribe({
      next: (data: {id: number, name: string}[]) => {
        this.contractors = data
        this.contractors.sort((a, b) => {
          if (a.name < b.name) return -1
          if (a.name > b.name) return 1
          return 0
        })
      }
    })
    this.service.getInitiators().subscribe({
      next: (data: {id: number, name: string}[]) => {
        this.initiators = data
        this.initiators.sort((a, b) => {
          if (a.name < b.name) return -1
          if (a.name > b.name) return 1
          return 0
        })
      }
    })
  }

  control(row: BuhMainTable): number {
    switch (row.mark.id) {
      case 0: return 3
      case 1: return 1
      case 2: return 2
      case 3: return 3
      default: return 3
    }
  }

  onChange(row: BuhMainTable, key: string, value: any = null): void {
    switch (key) {

      case "sumClosing":
        row.isChanged_sumClosing = true
        break

      case "about":
        row.isChanged_about = true
        row.about = this.dd.about[value.value]
        break

      case "mark":
        row.isChanged_mark = true
        row.mark = this.dd.mark[value.value]
        break

      case "status":
        row.isChanged_status = true
        row.status = this.dd.status[value.value]
        break

      case "title":
        row.isChanged_title = true
        break

    }
    this.isChanged = true
    const id = row.id
    this.editedRows = this.editedRows.filter(row => row.id !== id)
    this.editedRows.push(row)
  }

  allow(): boolean { return this.service.allow() }

  toggle(row: BuhMainTable, isCopy: boolean) {
    if (isCopy) {
      if (row.isActive_copyDate) row.isActive_copyDate = false
      else {
        this.table.map((row) => {
          row.isActive_copyDate = false
          row.isActive_origDate = false
        })
        row.isActive_copyDate = true
      }
    } else {
      if (row.isActive_origDate) row.isActive_origDate = false
      else {
        this.table.map((row) => {
          row.isActive_copyDate = false
          row.isActive_origDate = false
        })
        row.isActive_origDate = true
      }
    }
  }

  handleDateChange(row: BuhMainTable, date: NgbDateStruct, isCopy: boolean) {
    if (isCopy) {
      row.copyDate = new Date(date.year, date.month - 1, date.day)
      row.isActive_copyDate = false
      row.isChanged_copyDate = true
    } else {
      row.origDate = new Date(date.year, date.month - 1, date.day)
      row.isActive_origDate = false
      row.isChanged_origDate = true
    }
    this.isChanged = true
    const id = row.id
    this.editedRows = this.editedRows.filter(row => row.id !== id)
    this.editedRows.push(row)
  }

  isExistsInEditedRows(id: number): boolean {
    for (const row of this.editedRows) if (row.id === id) return true
    return false
  }

  submit(): void {
    this.service.edit(this.editedRows).subscribe({
      next: (data: boolean) => {
        if (data) {
          this.editedRows = []
          this.isChanged = false
          this.service.get().subscribe({
            next: (data: BuhMainTable[]) => {
              this.table = data
              console.log(data)
            }
          });
        }
      }
    });
  }

  filterToggle(key: number) {
    this.filterIsOpened.forEach((value, index) => {
      if (index !== key) this.filterIsOpened[index] = false
    })
    this.filterIsOpened[key] = !this.filterIsOpened[key]
  }

  contractorToggle(id: number) {

  }

}

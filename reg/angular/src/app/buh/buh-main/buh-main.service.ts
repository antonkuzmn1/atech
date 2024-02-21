import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { GlobalVariables } from "../../global-variables";
import { SessionService } from "../../session.service";
import { BuhDropdownList } from "../buh-dropdown-list";
import { BuhService } from "../buh.service";
import { BuhMainFilter } from "./buh-main-filter";
import { BuhMainTable } from "./buh-main-table";

@Injectable({ providedIn: 'root' })
export class BuhMainService {
    constructor(
        private http: HttpClient,
        private gv: GlobalVariables,
        private buhService: BuhService,
        private session: SessionService,
        private filter: BuhMainFilter
    ) { }
    private url: string = this.gv.auto() + "buh/table/main"

    public get(): Observable<BuhMainTable[]> {
        console.log(this.filter)
        return this.http.post<BuhMainTable[]>(this.url, JSON.stringify(this.filter), this.gv.cookie)
    }

    public edit(data: BuhMainTable[]): Observable<boolean> {
        console.log(data)
        return this.http.post<boolean>(this.url + "/edit", JSON.stringify(data), this.gv.cookie)
    }

    public getDropdowns(): Observable<BuhDropdownList> {
        return this.buhService.getDropdowns()
    }

    public allow(): boolean {
        return this.session.groups.some(id =>
            id === 1 ||
            id === 2)
    }

    public getContractors(): Observable<{ id: number, name: string }[]> {
        return this.http.get<{ id: number, name: string }[]>(this.gv.auto() + "buh/contractor", this.gv.cookie)
    }

    public getInitiators(): Observable<{ id: number, name: string }[]> {
        return this.http.get<{ id: number, name: string }[]>(this.gv.auto() + "buh/initiator", this.gv.cookie)
    }

}
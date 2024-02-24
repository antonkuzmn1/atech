import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { GlobalVariables } from "../common/backend.config";
import { BuhDropdownList } from "./classes/buh-dropdown-list";

@Injectable({providedIn: 'root'})
export class BuhService {
    constructor(
        private http: HttpClient,
        private gv: GlobalVariables
    ) {}
    private url: string = this.gv.auto() + "buh";

    getDropdowns(): Observable<BuhDropdownList> {
        return this.http.get<BuhDropdownList>(this.url + "/dropdown", this.gv.cookie);
    }

}
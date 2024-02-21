import { HttpClient } from "@angular/common/http";
import { Injectable, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { GlobalVariables } from "../../global-variables";
import { SessionService } from "../../session.service";
import { BuhImportResult } from "./buh-import-result";
import { BuhImportRow } from "./buh-import-row";

@Injectable({ providedIn: 'root' })
export class BuhImportService implements OnInit {
    constructor(
        private http: HttpClient,
        private session: SessionService,
        private globalVariables: GlobalVariables,
    ) { }
    private url: string = this.globalVariables.auto() + "buh/upload";
    private cred = { withCredentials: true };

    ngOnInit(): void {
        this.session.get();
    }

    private allow(): boolean {
        return this.session.groups.some(id =>
            id === 1 ||
            id === 2);
    }

    upload(jsonData: BuhImportRow[]): Observable<BuhImportResult> {
        if (!this.allow()) throw new Error("o kurwa! >:(");
        return this.http.post<BuhImportResult>(this.url, JSON.stringify(jsonData), this.cred);
    }
}
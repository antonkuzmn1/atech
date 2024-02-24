import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { GlobalVariables } from "./backend.config";

@Injectable({ providedIn: 'root' })
export class SessionService {
    constructor(
        private http: HttpClient,
        private gv: GlobalVariables,
    ) { }
    private url: string = this.gv.auto() + "session";

    public groups: number[] = [];

    get(): void {
        this.http.get<number[]>(this.url, this.gv.cookie).subscribe({
            next: (data: number[]) => this.groups = data
        });
    }

    auth(data: { login: string, password: string }): void {
        this.http.post<void>(this.url + "/auth", JSON.stringify(data), this.gv.cookie).subscribe({
            next: () => this.get()
        });
    }

    logout(): void {
        this.http.get<void>(this.url + "/logout", this.gv.cookie).subscribe({
            next: () => this.get()
        });
    }

    allow(ids: number[]): boolean {
        ids.push(1)
        return this.groups.some(id =>
            ids.includes(id));
    }

}
import { Injectable } from "@angular/core";

@Injectable()
export class GlobalVariables {

    public auto(): string {
        if (window.location.origin === "http://localhost:4200") return "http://localhost:8080/api/";
        else return (window.location.origin + "/api/");
    }

    public cookie: { withCredentials: boolean } = { withCredentials: true };

}
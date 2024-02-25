import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GlobalVariables } from '../../common/backend.config';
import { SessionService } from '../../common/session.service';
import { BuhService } from '../buh.service';

@Injectable({ providedIn: 'root' })
export class BuhAllService {
  constructor(
    private http: HttpClient,
    private gv: GlobalVariables,
    private buhService: BuhService,
    private session: SessionService,
    private filter: BuhAllFilter,
  ) {}
}

import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { HttpClientModule } from '@angular/common/http';
import { provideEnvironmentNgxMask } from 'ngx-mask';
import { routes } from './app.routes';
import { GlobalVariables } from './common/backend.config';
import { SessionService } from './common/session.service';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    importProvidersFrom(HttpClientModule),
    GlobalVariables,
    SessionService,
    provideEnvironmentNgxMask(),
  ],
};

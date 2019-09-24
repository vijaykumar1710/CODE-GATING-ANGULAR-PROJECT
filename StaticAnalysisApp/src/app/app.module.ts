import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VCGserviceComponent } from './vcgservice/vcgservice.component';
import { HomeComponent } from './home/home.component';

import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { PmdserviceComponent } from './pmdservice/pmdservice.component';
import { SimianserviceComponent } from './simianservice/simianservice.component';
import { CyvisserviceComponent } from './cyvisservice/cyvisservice.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { JacocoserviceComponent } from './jacocoservice/jacocoservice.component';
import {EditorModule} from 'primeng/editor';
import { EditorComponent } from './editor/editor.component';
import {InputTextareaModule} from 'primeng/inputtextarea';
import { QuillModule} from 'ngx-quill';
import { ServicesComponent } from './services/services.component'; 

@NgModule({
  declarations: [
    AppComponent,
    VCGserviceComponent,
    HomeComponent,
    PmdserviceComponent,
    SimianserviceComponent,
    CyvisserviceComponent,
    JacocoserviceComponent,
    EditorComponent,
    ServicesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    EditorModule,
    BrowserAnimationsModule,
    MatProgressSpinnerModule,
    InputTextareaModule,
    QuillModule,
    QuillModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

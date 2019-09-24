import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VCGserviceComponent } from './vcgservice/vcgservice.component';
import { HomeComponent } from './home/home.component';
import { PmdserviceComponent } from './pmdservice/pmdservice.component';
import { CyvisserviceComponent } from './cyvisservice/cyvisservice.component';
import { SimianserviceComponent } from './simianservice/simianservice.component';
import { JacocoserviceComponent } from './jacocoservice/jacocoservice.component';
import { EditorComponent } from './editor/editor.component';
import { ServicesComponent } from './services/services.component';


const routes: Routes = [
  {
    path : '',
    component : HomeComponent
  },{
    path : 'home',
    component : HomeComponent
  },{
    path : 'vcg',
    component :VCGserviceComponent
  },{
    path : 'pmd',
    component :PmdserviceComponent
  },{
    path : 'cyvis',
    component :CyvisserviceComponent
  },{
    path : 'simian',
    component :SimianserviceComponent
  },{
    path : 'jacoco',
    component : JacocoserviceComponent
  },{
    path : 'editor',
    component : EditorComponent
  },{
    path : 'allServices',
    component : ServicesComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

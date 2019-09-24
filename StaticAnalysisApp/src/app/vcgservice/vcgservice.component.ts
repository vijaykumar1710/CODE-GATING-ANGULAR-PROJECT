import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { Service } from 'src/app/shared/service';
import { DataserviceService } from '../shared/dataservice.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-vcgservice',
  templateUrl: './vcgservice.component.html',
  styleUrls: ['./vcgservice.component.css']
})
export class VCGserviceComponent implements OnInit {

  @Input()
  service : Service = {
    noOfWarnings_complexity_duplication : 0,
    finalResult : "",
    threshhold : 0,
    srcProject : ""
  };

  public hideShow:boolean = false;
  public hideBar : boolean = false;
  public hide : boolean = false;
  public success : boolean = false;
  showLoadingIndicator = true;
  sourcePath : "";
  //loading: boolean;

  @ViewChild('SourceForm',{static:false}) SourceForm: NgForm;
  constructor( private dataService : DataserviceService) { }

  ngOnInit() {
    //this.loading = false;
  }

  submit() {
    console.log(this.sourcePath);
    var res = encodeURI(this.sourcePath);
    if(this.sourcePath == undefined){
      this.hide= true;
      setTimeout(() => {
        this.hide= false;
      }, 2000);
    }else{
      this.hideShow = false;
      this.hideBar = true;
      this.dataService.getVCGResult(res).subscribe((vcg : Service)=>{
        this.service = vcg;
        this.hideBar = false;
        this.hideShow = true;
        console.log(vcg);
      });
    } 
  }
}

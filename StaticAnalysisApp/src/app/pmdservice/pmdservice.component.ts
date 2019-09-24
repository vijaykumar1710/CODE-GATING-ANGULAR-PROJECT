import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { Service } from 'src/app/shared/service';
import { DataserviceService } from '../shared/dataservice.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-pmdservice',
  templateUrl: './pmdservice.component.html',
  styleUrls: ['./pmdservice.component.css']
})
export class PmdserviceComponent implements OnInit {

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

  sourcePath : "";

  @ViewChild('SourceForm',{static:false}) SourceForm: NgForm;
  constructor( private dataService : DataserviceService) { }

  ngOnInit() {
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
      this.dataService.getPMDResult(res).subscribe((pmd : Service)=>{
        this.service = pmd;
        this.hideBar = false;
        this.hideShow = true;
        console.log(pmd);
      });
    } 
  }
}


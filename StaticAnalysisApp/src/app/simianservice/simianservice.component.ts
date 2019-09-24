import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Service } from '../shared/service';
import { DataserviceService } from '../shared/dataservice.service';

@Component({
  selector: 'app-simianservice',
  templateUrl: './simianservice.component.html',
  styleUrls: ['./simianservice.component.css']
})
export class SimianserviceComponent implements OnInit {

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
  NoOfLines : number;
  

  @ViewChild('SourceForm',{static:false}) SourceForm: NgForm;
  constructor(private dataService : DataserviceService) { }

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
      this.dataService.getSimianResult(res).subscribe((simian : Service)=>{
        this.service = simian;
        this.hideBar = false;
        this.hideShow = true;
        console.log(simian);
      });
    }
  }

  set(){
    if(this.NoOfLines == undefined){
      this.hide= true;
      setTimeout(() => {
        this.hide= false;
      }, 2000);  
    }else{
     var  v = {"duplicateLinesThreshold":this.NoOfLines};
      this.dataService.postSimianConfig(v);
      this.success = true ;
      setTimeout(() => {
        this.success= false;
      }, 2000);
    }
  }
}

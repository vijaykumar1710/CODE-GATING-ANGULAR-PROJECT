import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Service } from '../shared/service';
import { DataserviceService } from '../shared/dataservice.service';

@Component({
  selector: 'app-cyvisservice',
  templateUrl: './cyvisservice.component.html',
  styleUrls: ['./cyvisservice.component.css']
})
export class CyvisserviceComponent implements OnInit {

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
      this.dataService.getCyvisResult(res).subscribe(( cyvis : Service)=>{
        this.service = cyvis;
        this.hideBar = false;
        this.hideShow = true;
        console.log(cyvis);
      });
    } 
  }

}

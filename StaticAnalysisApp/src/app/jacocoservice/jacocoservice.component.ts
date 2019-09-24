import { Component, OnInit, Input } from '@angular/core';
import { Jacoco } from '../shared/jacocoService';
import { DataserviceService } from '../shared/dataservice.service';

@Component({
  selector: 'app-jacocoservice',
  templateUrl: './jacocoservice.component.html',
  styleUrls: ['./jacocoservice.component.css']
})
export class JacocoserviceComponent implements OnInit {

  @Input()
  jacoco : Jacoco = {
    timeToRunTest: 0,
    timeThreshold: 0,
    codeCoverage: 0,
    threshold: 0,
    finalResult: ''  
  };

  sourcePath : "";

  public hideShow:boolean = false;
  public hideBar : boolean = false;
  public hide : boolean = false;
  public success : boolean = false;

  constructor(private dataService : DataserviceService) { }

  ngOnInit() {
  }

  submit(){
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
      this.dataService.getJacocoResult(res).subscribe((result : Jacoco)=>{
        this.jacoco = result;
        this.hideBar = false;
        this.hideShow = true;
        console.log(result);
      });
    }
  }

}

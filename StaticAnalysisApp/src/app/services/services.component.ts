import { Component, OnInit, Input } from '@angular/core';
import { DataserviceService } from '../shared/dataservice.service';
import { AllService } from '../shared/allService';
import { GitStatus } from '../shared/gitStatus';

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.css']
})
export class ServicesComponent implements OnInit {

  @Input()
  allservice : AllService = {
    projectPath: '',
    noOfWarnings: 0,
    codeCoverage: 0,
    cyclomaticComplexity: 0,
    codeDuplication: 0,
    securityIssuesCount: 0,
    finalDecision: '',
    timeToRunTests:0
  };

  gitsatus : GitStatus ={
    status : '',
    sourcePath : ''
  }

  usePreviousResultsAsThreshold :  boolean;
  sourcePath : '';
  public hideBar : boolean = false;
  public hideShow:boolean = false;
  public hide : boolean = false;
  public clonefail: boolean = false;
  public clonepass: boolean = false;
  public gitload: boolean = false; 
  showLoadingIndicator = true;

  
  display='none';
  

  constructor(private dataService : DataserviceService) { }

  ngOnInit() {
  }

  submit(){
    console.log(this.sourcePath);
    this.clonefail = false;
    this.clonepass= false;
    this.usePreviousResultsAsThreshold=false;
    if(this.sourcePath == undefined){
      this.hide=true;
    }else{
      if(this.sourcePath.includes('.git')){
        this.gitload = true;
          this.dataService.getGitCloneRepo(this.sourcePath).subscribe((clone:GitStatus)=>{
          this.gitsatus = clone; 
          if (clone.status == "Success") {
              this.clonepass = true;
          } else {
              this.clonefail = true;
          }
          this.gitload = false; 
          var res = encodeURI(this.gitsatus.sourcePath);
          this.usePreviousResultsAsThreshold=false;
          this.runAllServices(res);
        });
      }else{
        this.runAllServices(encodeURI(this.sourcePath));
      }
    }

  }

  set(){
    this.openModalDialog()
  }

  closeModalDialog(){
    this.display='none'; //Set block css
  }

  openModalDialog(){
    this.display='block'; //set none css after close dialog
  }

  runAllServices(res : string ){
    if(this.sourcePath == undefined){
      this.hide= true;
      setTimeout(() => {
        this.hide= false;
      }, 2000);
    }else{
      this.hideShow = false;
      this.hideBar = true;
      this.dataService.getAllServiceResult(res,this.usePreviousResultsAsThreshold).subscribe((all : AllService)=>{
       this.allservice= all;
       console.log(this.allservice);
        this.hideBar = false;
        this.hideShow = true;
        console.log(all);
      });
    }
  }
}
//https://github.com/jglick/simple-maven-project-with-tests.git
//https://github.com/LableOrg/java-maven-junit-helloworld.git 
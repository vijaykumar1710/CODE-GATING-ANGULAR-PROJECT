import { Component, OnInit, Input } from '@angular/core';
import { DataserviceService } from '../shared/dataservice.service';
import { Service } from '../shared/service';
import { SourcePath } from '../shared/sourcepath';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  @Input()
  service : Service = {
    noOfWarnings_complexity_duplication : 0,
    finalResult : "",
    threshhold : 0,
    srcProject : ""
  };

  @Input()
  sourcepath : SourcePath = {
    editorsourcepath : ""
  };

  sourceCode : "";
  display='none';

  constructor(private dataService : DataserviceService) { }

  ngOnInit() {
  }

  submit(){
      console.log(this.sourceCode);
      var res = encodeURI(this.sourceCode);
       this.dataService.getEditorPath(res).subscribe((sourcepath : SourcePath)=>{
          this.sourcepath= sourcepath; 
          console.log(sourcepath.editorsourcepath);
          this.dataService.getEditorSimianResult(encodeURI(sourcepath.editorsourcepath)).subscribe((simian : Service)=>{
            this.service = simian;
            this.openModalDialog();
            console.log(this.service);
          });
       });
  }

  closeModalDialog(){
    this.display='none'; //Set block css
  }

  openModalDialog(){
    this.display='block'; //set none css after close dialog
  }
}

import { Injectable } from '@angular/core';
import { GitStatus } from './gitStatus';
import { DataserviceService } from './dataservice.service';

@Injectable({
  providedIn: 'root'
})
export class GitdownloadService {

  constructor(private giturl : string, private dataService : DataserviceService) { }

  status : GitStatus ={
    status : '',
    sourcePath : ''
  };

download(giturl :string ){
        return this.dataService.getGitCloneRepo(giturl).subscribe((status : GitStatus)=>{
            this.status = status;
        });
}

validate(path : string){
    return path.includes('.git');
}

}

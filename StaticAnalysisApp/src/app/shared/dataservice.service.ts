import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Service} from './service';
import { SourcePath } from './sourcepath';
import { AllService } from './allService';
import { GitStatus } from './gitStatus';
import { Jacoco } from './jacocoService';

@Injectable({
  providedIn: 'root'
})
export class DataserviceService {

  params = new HttpParams();

  vcgServiceURI : string = 'http://localhost:8085/gating/security/vcg';
  pmdServiceURI : string = 'http://localhost:8085/gating/warnings/pmd';
  cyvisServiceURI : string = 'http://localhost:8085/gating/complexity/cyvis';
  simianServiceURI : string = 'http://localhost:8085/gating/duplication/simian';
  editorSimianServiceURI : string = 'http://localhost:8085/gating/duplication/editorSimian';
  simianConfigURI : string = 'http://localhost:8085/gating/simian/config/new';
  editorUri  : string = 'http://localhost:8085/gating/editor';
  allServiceUri : string = 'http://localhost:8085/gating/allservices';
  gitcloneuri : string = 'http://localhost:8085/gating/clonerepo';
  jacocoServiceURI : string = 'http://localhost:8085/gating/coverage/jacoco';

  constructor(private http:HttpClient, private httpClient : HttpClientModule) {
   }

   getVCGResult(sourcepath : string ) : Observable<Service>{
      return this.http.get<Service>(`${this.vcgServiceURI}?sourceCodePath=${sourcepath}`);
   }

   getPMDResult(sourcepath : string ) : Observable<Service>{
    return this.http.get<Service>(`${this.pmdServiceURI}?sourceCodePath=${sourcepath}`);
   }

   getCyvisResult(sourcepath : string ) : Observable<Service>{
    return this.http.get<Service>(`${this.cyvisServiceURI}?sourceCodePath=${sourcepath}`);
   }

   getSimianResult(sourcepath : string ) : Observable<Service>{
    return this.http.get<Service>(`${this.simianServiceURI}?sourceCodePath=${sourcepath}`);
   }

   getEditorSimianResult(sourcepath : string ) : Observable<Service>{
    return this.http.get<Service>(`${this.editorSimianServiceURI}?sourceCodePath=${sourcepath}`);
   }


   postSimianConfig(config : any){
     return this.http.post(this.simianConfigURI,config).subscribe(
      data => console.log('success', data),
      error => console.log('oops', error)
    );
   }

   getEditorPath(code :string) : Observable<SourcePath> {
      return this.http.get<SourcePath>(`${this.editorUri}?sourceCode=${code}`);
   }

   getAllServiceResult(sourcepath : string, value : boolean): Observable<AllService>{
    return this.http.get<AllService>(`${this.allServiceUri}?sourceCodePath=${sourcepath}&usePreviousResultsAsThreshold=${value}`);
   }

   getGitCloneRepo(giturl : string ) : Observable<GitStatus>{
      return this.http.get<GitStatus>(`${this.gitcloneuri}?githubLink=${giturl}`);
   }

   getJacocoResult(sourcepath : string) : Observable<Jacoco>{
      return this.http.get<Jacoco>(`${this.jacocoServiceURI}?sourceCodePath=${sourcepath}`);
   }

}

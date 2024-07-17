import { Component, forwardRef, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { ActorsViewModelService } from './services.service';
import { FormsModule } from '@angular/forms';
import { CommonModule, DatePipe } from '@angular/common';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Subscription } from 'rxjs';
import { CapitalizePipe, ErrorMessagePipe, } from '../../lib/my-core';

@Component({
  selector: 'app-actors',
  templateUrl: './tmpl-host.component.html',
  styleUrls: ['./component.component.css'],
  standalone: true,
  imports: [
    forwardRef(() => ActorsAddComponent),
    forwardRef(() => ActorsEditComponent),
    forwardRef(() => ActorsViewComponent),
    forwardRef(() => ActorsListComponent),
  ],
})
export class ActorsComponent implements OnInit, OnDestroy {
  constructor(protected vm: ActorsViewModelService) { }

  public get VM(): ActorsViewModelService { return this.vm; }

  ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
}

@Component({
  selector: 'app-actors-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./component.component.css'],
  standalone: true,
  imports: [ CommonModule, CapitalizePipe ],
})
export class ActorsListComponent implements OnInit, OnDestroy {
  constructor(protected vm: ActorsViewModelService) { }

  public get VM(): ActorsViewModelService { return this.vm; }

  ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
}

@Component({
  selector: 'app-actors-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./component.component.css'],
  standalone: true,
  imports: [ FormsModule, ErrorMessagePipe, CommonModule, ],
})
export class ActorsAddComponent implements OnInit {
  constructor(protected vm: ActorsViewModelService) {}
  public get VM(): ActorsViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.add();
  }
}

@Component({
  selector: 'app-actors-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./component.component.css'],
  standalone: true,
  imports: [ FormsModule, ErrorMessagePipe, CommonModule, ],
})
export class ActorsEditComponent implements OnInit, OnDestroy {
  private obs$?: Subscription;
  constructor(
    protected vm: ActorsViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): ActorsViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe((params: ParamMap) => {
      const id = parseInt(params?.get('id') ?? '');
      if (id) {
        this.vm.edit(id);
      } else {
        this.router.navigate(['/404.html']);
      }
    });
  }
  ngOnDestroy(): void {
    this.obs$!.unsubscribe();
  }
}
@Component({
  selector: 'app-actors-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./component.component.css'],
  standalone: true,
  imports: [DatePipe, CommonModule,],
})
export class ActorsViewComponent implements OnChanges {
  @Input() id?: string;
  constructor(
    protected vm: ActorsViewModelService,
    protected router: Router
  ) {}
  public get VM(): ActorsViewModelService {
    return this.vm;
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}

export const FILMS_COMPONENTS = [
  ActorsComponent,
  ActorsListComponent,
  ActorsAddComponent,
  ActorsEditComponent,
  ActorsViewComponent,
];
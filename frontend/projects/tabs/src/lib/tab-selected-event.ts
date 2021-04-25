import {BusEvent} from 'projects/event/src/lib/bus-event';
import {Tab} from 'projects/tabs/src/lib/tab';

export class TabSelectedEvent extends BusEvent {

  public static readonly CHANNEL = 'tab-selected';

  constructor(public tab: Tab) {
    super(TabSelectedEvent.CHANNEL);
  }
}

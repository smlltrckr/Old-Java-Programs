import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
/**
 * 
 * @author Sam
 *
 */

public class Calendar 
{
	public static void main(String [] arg) throws IOException, ParseException
	{
		String choice;
		Scanner in = new Scanner(System.in);
		Event event;
		Eventstorage eventStorage = new Eventstorage();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(3);
		FileWriter file = new FileWriter("reservation.txt");
		PrintWriter printLine = new PrintWriter(file);
		SimpleDateFormat eventFormat = new SimpleDateFormat("YYYY\n   MMMM d");
		SimpleDateFormat pFormatYear = new SimpleDateFormat("YYYY");
		SimpleDateFormat pFormatMonthDay = new SimpleDateFormat("MMMM d");
		SimpleDateFormat dayFormat = new SimpleDateFormat("E, MMM d");
		SimpleDateFormat searchFormat = new SimpleDateFormat("MM/dd/YYYY");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM YYYY");
		SimpleDateFormat monthName = new SimpleDateFormat("MMMM");
		Date day = calendar.getTime();
		Date prevDate;
		Date nextDate;
		Date keyToDate;
		int weekDay;
		String delm = "[/]";
		String[] keyTo;
		
		do{
			System.out.println("  " + monthFormat.format(day));
			System.out.println(" Su  Mo  Tu  We  Th  Fr  Sa ");
			weekDay = calendar.getFirstDayOfWeek();
			
			for(int n = 0; n < weekDay; n++)
			{
				System.out.print("    ");
			}
			for(int m = 1; m <= calendar.getActualMaximum(calendar.DAY_OF_MONTH); m++)
			{
				if(m <= 9)
				{
					System.out.print("  " + m + " ");
				}
				else
				{
					System.out.print(" " + m + " ");
				}
				if((weekDay + m) % 7 == 0)
				{
					System.out.println();
				}
			}
			System.out.println("\n");
			System.out.println("Select one of the following options:\n" +
					"[V]iew by  [C]reate, [G]o to [E]vent list [D]elete [Q]uit");
			choice = in.nextLine();
			switch(choice.toLowerCase())
			{
				case "v":	System.out.println("[D]ay view or [M]onth view");
							choice = in.nextLine();
							switch(choice.toLowerCase())
							{
								case "d":	System.out.println(dayFormat.format(day));
											if(eventStorage.getAllEvents().containsKey(searchFormat.format(day)))
											{	
												if(!eventStorage.getDayEvents(searchFormat.format(day)).isEmpty())
												{
													for(int b = 0; b < eventStorage.getDayEvents(searchFormat.format(day)).size(); b++)
													{
														System.out.println( eventStorage.getDayEvents(searchFormat.format(day)).get(b).getEventTitle() + " " +
																eventStorage.getDayEvents(searchFormat.format(day)).get(b).getStartTime() + " - " + 
																eventStorage.getDayEvents(searchFormat.format(day)).get(b).getEndTime());
													}
												}
											}
											do{
												System.out.println("\n[P]revios or [N]ext or [M]ain menu");
												choice = in.nextLine();
												switch(choice.toLowerCase())
												{
													case "p":	calendar.add(calendar.DATE, -1);
																prevDate = calendar.getTime();
																System.out.println(dayFormat.format(prevDate));
																if(eventStorage.getAllEvents().containsKey(searchFormat.format(prevDate)))
																{
																	if(!eventStorage.getDayEvents(searchFormat.format(prevDate)).isEmpty())
																	{
																		for(int b = 0; b < eventStorage.getDayEvents(searchFormat.format(prevDate)).size(); b++)
																		{
																			System.out.println( eventStorage.getDayEvents(searchFormat.format(prevDate)).get(b).getEventTitle() + " " +
																					eventStorage.getDayEvents(searchFormat.format(prevDate)).get(b).getStartTime() + " - " + 
																					eventStorage.getDayEvents(searchFormat.format(prevDate)).get(b).getEndTime());
																		}
																	}
																}
																break;
													case "n":	calendar.add(calendar.DATE, +1);
																nextDate = calendar.getTime();
																System.out.println(dayFormat.format(nextDate));
																if(eventStorage.getAllEvents().containsKey(searchFormat.format(nextDate)))
																{
																	if(!eventStorage.getDayEvents(searchFormat.format(nextDate)).isEmpty())
																	{
																		for(int b = 0; b < eventStorage.getDayEvents(searchFormat.format(nextDate)).size(); b++)
																		{
																			System.out.println(eventStorage.getDayEvents(searchFormat.format(nextDate)).get(b).getEventTitle() + " " +
																						eventStorage.getDayEvents(searchFormat.format(nextDate)).get(b).getStartTime() + " - " + 
																						eventStorage.getDayEvents(searchFormat.format(nextDate)).get(b).getEndTime());
																		}
																	}
																}
																break;
													case "m":	
																break;
													default	:	System.out.println("Invalid Option!");
																break;
												}
											}while(!choice.equals("m"));
											break;
								case "m":	System.out.println("  " + monthFormat.format(day));
											System.out.println(" Su  Mo  Tu  We  Th  Fr  Sa ");
											
											weekDay = calendar.getFirstDayOfWeek();
											for(int n = 0; n < weekDay; n++)
											{
												System.out.print("    ");
											}
											for(int m = 1; m <= calendar.getActualMaximum(calendar.DAY_OF_MONTH); m++)
											{
												if(m <= 9)
												{
													System.out.print("  " + m + " ");
												}
												else
												{
													System.out.print(" " + m + " ");
												}
												if((weekDay + m) % 7 == 0)
												{
													System.out.println();
												}
											}
											System.out.println("\n");
											do{
												System.out.println("[P]revios or [N]ext or [M]ain menu");
												choice = in.nextLine();
												switch(choice.toLowerCase())
												{
													case "p":	if(monthName.format(calendar.getTime()).equals("January"))
																{
																	calendar.roll(calendar.YEAR, false);
																}
																calendar.roll(calendar.MONTH, false);
																day = calendar.getTime();
																System.out.println(" " + monthFormat.format(day));
																System.out.println(" Su  Mo  Tu  We  Th  Fr  Sa ");
																
																weekDay = calendar.getFirstDayOfWeek();
																for(int n = 0; n < weekDay; n++)
																{
																	System.out.print("    ");
																}
																for(int m = 1; m <= calendar.getActualMaximum(calendar.DAY_OF_MONTH); m++)
																{
																	if(m <= 9)
																	{
																		System.out.print("  " + m + " ");
																	}
																	else
																	{
																		System.out.print(" " + m + " ");
																	}
																	if((weekDay + m) % 7 == 0)
																	{
																		System.out.println();
																	}
																}
																System.out.println("\n");
																break;
													case "n":	if(monthName.format(calendar.getTime()).equals("December"))
																{
																	calendar.roll(calendar.YEAR, true);
																}
																calendar.roll(calendar.MONTH, true);
																day = calendar.getTime();
																System.out.println(" " + monthFormat.format(day));
																break;
													case "m":	
																break;
													default	:	System.out.println("Invalid Option!");
																break;
												}
											}while(!choice.equals("m"));
											break;
								default	:	System.out.println("Invalid Option!");
											break;
							}
							break;
				case "c":	event = new Event();
							System.out.println("Enter title of event:");
							event.setTitle(in.nextLine());
							System.out.println("Enter date of event MM/DD/YYYY:");
							event.setDate(in.nextLine());
							System.out.println("Enter Start time and/or End time separated by a space\n" + 
										"(Example format: 06:00 for 6AM and 15:30 for 3:30PM)");
							event.setTimeRange(in.nextLine());
							eventStorage.addEvent(event.getDate(), event);
							System.out.println("Event created.");
							break;
				case "g":	System.out.println("Enter date of event MM/DD/YYYY:");
							choice = in.nextLine();
							keyTo = choice.split(delm);
							calendar.set(Integer.parseInt(keyTo[2]),Integer.parseInt(keyTo[0]) - 1,Integer.parseInt(keyTo[1]));
							keyToDate = calendar.getTime();
							System.out.println(dayFormat.format(keyToDate));
							if(eventStorage.getAllEvents().containsKey(choice))
							{
								if(!eventStorage.getDayEvents(choice).isEmpty())
								{
									for(int i = 0; i < eventStorage.getDayEvents(choice).size(); i++)
									{
										System.out.println(eventStorage.getDayEvents(choice).get(i).getEventTitle() + " " +
													eventStorage.getDayEvents(choice).get(i).getStartTime() + " - " + 
													eventStorage.getDayEvents(choice).get(i).getEndTime());
									}
								}
							}
							break;
							
				case "e":	for(String key : eventStorage.getAllEvents().keySet())
								{
									keyTo = key.split(delm);
									calendar.set(Integer.parseInt(keyTo[2]),Integer.parseInt(keyTo[0]) - 1,Integer.parseInt(keyTo[1]));
									keyToDate = calendar.getTime();
									System.out.println(eventFormat.format(keyToDate));
									for(int a = 0; a < eventStorage.getDayEvents(key).size(); a++)
									{
										System.out.println("\t" + eventStorage.getDayEvents(key).get(a).getStartTime() + " - " + 
												eventStorage.getDayEvents(key).get(a).getEndTime() + " " + 
											eventStorage.getDayEvents(key).get(a).getEventTitle());
									}
								}
							System.out.println();
							break;
				case "d":	System.out.println("[S]elected or [A]ll\n");
							choice = in.nextLine();
							switch(choice.toLowerCase())
							{
								case "s":	System.out.println("Enter the date to be deleted MM/DD/YYYY.");
											choice = in.nextLine();
											eventStorage.clearEvent(choice);
											System.out.println("Events on " + choice + " have been deleted.");
											break;
								case "a":	eventStorage.clearAllEvents();
											System.out.println("All events deleted.");
											break;
								default	:	System.out.println("Invalid Option!");
											break;
							}
							break;
				case "q":	for(String key : eventStorage.getAllEvents().keySet())
							{
								keyTo = key.split(delm);
								calendar.set(Integer.parseInt(keyTo[2]),Integer.parseInt(keyTo[0]) - 1,Integer.parseInt(keyTo[1]));
								keyToDate = calendar.getTime();
								printLine.printf("%s" + "%n", pFormatYear.format(keyToDate));
								printLine.printf("%s" + "%n", "  " + pFormatMonthDay.format(keyToDate));
								for(int a = 0; a < eventStorage.getDayEvents(key).size(); a++)
								{
									printLine.printf("%s" + "%n", "  " + eventStorage.getDayEvents(key).get(a).getStartTime() + " - " + 
											eventStorage.getDayEvents(key).get(a).getEndTime() + " " + 
										eventStorage.getDayEvents(key).get(a).getEventTitle() + "\n");
								}
								
							}
							file.close();
							System.out.println("Event(s) have been saved to: reservation.txt");
							break;
				default : 	System.out.println("Invalid Option!");
							break;
			}
		}while(!choice.equals("q"));
		
		in.close();
	}
}

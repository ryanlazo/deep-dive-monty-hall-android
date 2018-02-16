#Monty Hall Simulation

##About the Problem
The Monty Hall problem was named after the game show "Lets Make a Deal"
that was hosted by Monty Hall. The problem is given three doors, two of which
contain "goats" or zonks, and one that contains a "Car" or prize, you have to
choose one without knowing whats behind any of the doors. After you make an
initial choice one of the zonk door is revealed to you and you have the choice
to stay with your original choice or switch to the door that has not been
revealed. The choice considered a "win" is when the door you choose contained
the "car".

##How we approached the Problem
Given the three frame objects given to us that represented the doors we used
an onClickListener to define the selected door as the <code>Field</code> <code>chosen_door</code>.
after the initial choice is made the <code>Stage</code> is advanced to <code>DOOR_CHOSEN
</code>. After which the <code>Buttons</code> <code>Stay</code> and <code>Switch</code> appear and when
clicked will cause the <code>chosen_door</code> to either stay the same or switch respectively. After 
that decision is made the <code>Stage</code> is advanced to <code>END</code> and the rest of the doors open to show their contents, revealing
wheter or not the <code>chosen_door</code> was a <code>CAR</code> or <code>GOAT</code>.
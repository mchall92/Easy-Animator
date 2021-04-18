# Easy-Animator

This version of the project provides a command line interface to let the user customize a view to generate an animation.
The project uses a Reader to parse the data in the input file, then use a builder to pass all these data into the model.
The things user can customize are: input file | output method | playing speed(optional) | view to show input file should
be in a specific format so that data can be parsed correctly.

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
Text view:
provides the information of all shapes, then provide information of all animations ordered by start time. output in any
format

Visual view:
provides the visual interface to let user directly view the animation. output only in Java

Svg view:
provides the svg format view to let user both directly view the animation or view the tags to get specific information..
output only in .svg files can view tags in text editor can view animation in browsers
> > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > > >

Changes from the last version in the model part:
1.Changed all double type fields to int type 2.Added mehods to genetate svg log 3.Changed method signature so that speed
can be injected into the model 4.Add the coordinate of view box borders as fields in WindowImpl 5.Change the order from
appearTime order to Declare order.

